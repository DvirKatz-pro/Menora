package com.example.Menora.Service;

import com.example.Menora.Repositories.Entities.Root;
import com.example.Menora.Repositories.XMLRepository;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Service
@Component
public class FileProcessing {
    @Value("${file.input.path}")
    private String fileInputPath;
    @Value("${file.backup.path}")
    private String fileBackUpPath;

    XMLRepository xmlRepository;
    @Autowired
    public FileProcessing(XMLRepository xmlRepository) {
        this.xmlRepository = xmlRepository;
    }

    @PostConstruct
    public void processBackupData() throws JAXBException {
        File allFiles = new File(fileBackUpPath);
        if (allFiles.listFiles() != null) {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller mar= context.createUnmarshaller();
            for (File f: Objects.requireNonNull(allFiles.listFiles())) {
                try {
                    Root root = (Root) mar.unmarshal(f);
                    xmlRepository.addXmlToRepository(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(fixedDelayString = "${file.input.delay}")
    public void  processInputFiles() throws JAXBException {
        File allFiles = new File(fileInputPath);
        if (allFiles.listFiles() != null) {
            JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller mar= context.createUnmarshaller();
            for (File f: Objects.requireNonNull(allFiles.listFiles())) {
                try {
                    Root root = (Root) mar.unmarshal(f);
                    xmlRepository.addXmlToRepository(root);
                    f.renameTo(new File(fileBackUpPath + f.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
