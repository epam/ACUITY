/*
 * Copyright 2021 The University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acuity.sdtm.data.provider.azure;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.file.CloudFileClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("azure-storage")
@Configuration
public class AzureStorageConfiguration {

    @Autowired
    private AzureStorageProperties properties;

    @SneakyThrows
    @Bean
    public CloudStorageAccount storageAccount() {
        return CloudStorageAccount.parse(properties.connectionString());
    }

    @Bean
    public CloudFileClient fileClient(CloudStorageAccount storageAccount) {
        return storageAccount.createCloudFileClient();
    }

    @Bean
    public AzureFileStorageDataProvider fileStorageDataProvider(CloudFileClient fileClient) {
        return new AzureFileStorageDataProvider(fileClient);
    }

}
