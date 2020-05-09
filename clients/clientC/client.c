#include <stdlib.h>
#include <stdio.h>
#include <curl/curl.h>
#include <string.h>

#define UPLOAD_URL "http://192.168.56.1:8080/file/savePublicFile/"

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

        // Set the URL that receives this POST and the associated form
        char* url = malloc(sizeof(char) * 80);
        strcpy(url, UPLOAD_URL);
        curl_easy_setopt(curl, CURLOPT_URL, strcat(url, fileName));
        curl_easy_setopt(curl, CURLOPT_MIMEPOST, form);
    
        // Perform the request and check for errors
        CURLcode res = curl_easy_perform(curl);
        if(CURLE_OK != res) {
            fprintf(stderr, "Request failed: %s\n", curl_easy_strerror(res));
        }
    
        curl_easy_cleanup(curl);    
        curl_mime_free(form);
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

int main() {
    printf("\nBienvenu sur OWNDRIVE\n");
    
    int choice;
    do {
        switch (choice = mainMenu()) {
            case 1:
                upload();
                break;
            
            case 2:
                printf("Preview\n");
                break;
            
            case 3:
                printf("Download\n");
                break;
        }
    } while (0 != choice);
}