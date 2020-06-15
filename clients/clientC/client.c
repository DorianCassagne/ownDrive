#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <curl/curl.h>
#include <string.h>

#define UPLOAD_URL "http://192.168.56.1:8080/file/savePublicFile/"
#define DOWNLOAD_URL "http://192.168.56.1:8080/file/getPublicFile/"

static size_t writeData(void *ptr, size_t size, size_t nmemb, void *stream) {
  size_t written = fwrite(ptr, size, nmemb, (FILE *)stream);
  return written;
}

int mainMenu() {
    int choice;
    do {
        printf("\nMenu principal\n");
        printf(" 1 - Mettre un fichier en ligne\n");
        printf(" 2 - Consulter un fichier\n");
        printf(" 3 - Télécharger un fichier\n");
        printf(" 0 - Quitter\n");
        printf("Entrez le chiffre correspondant à la fonction souhaitée : ");
        
        scanf("%d", &choice);
    } while (0 > choice || 3 < choice);

    return choice;
}

void performCurlRequest(CURL* curl, char* curlOkMessage) {
    CURLcode res = curl_easy_perform(curl);
    if (CURLE_OK != res) {
        fprintf(stderr, "Request failed: %s\n", curl_easy_strerror(res));
    } else {
        printf("%s", curlOkMessage);
    }
}

void sendFile(char* path, char* fileName) {    
    // Init curl API
    curl_global_init(CURL_GLOBAL_ALL);
    CURL* curl = curl_easy_init();
    if (curl) {
        // Create the form and fill in file field
        curl_mime* form = curl_mime_init(curl);
        curl_mimepart* field = curl_mime_addpart(form);
        curl_mime_name(field, "file");
        curl_mime_filedata(field, path);

        // Set the URL and the associated form
        char* url = malloc(sizeof(char) * 80);
        strcpy(url, UPLOAD_URL);
        curl_easy_setopt(curl, CURLOPT_URL, strcat(url, fileName));
        curl_easy_setopt(curl, CURLOPT_MIMEPOST, form);
    
        performCurlRequest(curl, "\nUpload completed\n");
    
        curl_easy_cleanup(curl);    
        curl_mime_free(form);
    }
}

void requestFile(char* path, bool write) {
    // Init curl API
    CURL* curl = curl_easy_init();
    if (curl) {
        // Set the URL
        char* url = malloc(sizeof(char) * 80);
        strcpy(url, DOWNLOAD_URL);
        curl_easy_setopt(curl, CURLOPT_URL, strcat(url, path));

        if (write) {
            // Set the write function
            curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeData);

            printf("%s", path);
            FILE* file = fopen(path, "wb");
            if (file) {
                // Set the file that will receive the downloaded data
                curl_easy_setopt(curl, CURLOPT_WRITEDATA, file);
                
                performCurlRequest(curl, "\nDownload complete\n");

                fclose(file);
            }
        } else {
            performCurlRequest(curl, "");
        }
    
        curl_easy_cleanup(curl);
    }
}

void upload() {
    printf("\nUpload\n");

    FILE* file;
    char* path = malloc(sizeof(char));
    do {
        printf("Entrez le chemin du fichier à mettre en ligne : ");
        scanf("%s", path);

        file = fopen(path, "r");
    } while (NULL == file);
    fclose(file);

    char* fileName = malloc(sizeof(char));
    printf("Entrez le nom du fichier à l'arrivée : ");
    scanf("%s", fileName);

    sendFile(path, fileName);
}

void preview() {
    printf("\nPreview\n");

    char* path = malloc(sizeof(char));
    printf("Entrez le chemin du fichier à afficher : ");
    scanf("%s", path);

    requestFile(path, false);
}

void download() {
    printf("\nDownload\n");

    char* path = malloc(sizeof(char));
    printf("Entrez le chemin du fichier à télécharger : ");
    scanf("%s", path);

    requestFile(path, true);
}

int main() {
    printf("\nBienvenu sur OWNDRIVE\n");
    
    int choice;
    do {
        switch (choice = mainMenu()) {
            case 1:
                upload();
                break;
            
            case 2:
                preview();
                break;
            
            case 3:
                download();
                break;
        }
        printf("\n");
    } while (0 != choice);
}