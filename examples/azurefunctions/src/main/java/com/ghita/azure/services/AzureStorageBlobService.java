package com.ghita.azure.services;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

public final class AzureStorageBlobService {

    private final BlobServiceClient blobServiceClient;


    public AzureStorageBlobService(final String blobEndpoint) {
        this.blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(blobEndpoint)
                .buildClient();
    }

    public String getTextBlobContent(final String container, final String blobName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);

        BinaryData binaryData = containerClient.getBlobClient(blobName).getBlockBlobClient()
                .downloadContent();
        return binaryData.toString();
    }

}
