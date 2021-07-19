package com.ghita.azureblob.runners;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.ListBlobsOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RunnerAzureBlob implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        String connectStr = System.getenv("AZURE_STORAGE_CONNECTION_STRING");

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .sasToken(connectStr).buildClient();


        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("datalake");

        System.out.println("\nListing blobs...");
        // List the blob(s) in the container.
//        for (BlobItem blobItem : containerClient.listBlobs()) {
//            System.out.println("\t" + blobItem.getName());
//        }

        ListBlobsOptions listBlobsOptions = new ListBlobsOptions()
                .setPrefix("transientZone/Data/");
        containerClient.listBlobs(listBlobsOptions, null)
                .stream()
                .forEach(item -> {
                    System.out.println(item.getName() + " : " + item.getProperties());
                });

//        blobServiceClient.listBlobContainers()
//                .
//                .iterableByPage()
//                .forEach(e -> System.out.println(e.getValue().stream()
//                        .map(c -> c.getName())
//                        .collect(Collectors.joining(","))));
    }

}
