# cloud-data-deduplication
Its an web application for cloud data depublication in encrypted domain
image encryption is done using AES and hash is calculated using SHA-512.

Whenever user want to upload the image on cloud , image is encrypted and hash is calculated on client side.
Hash is sent to server and it is compared with all the hash stored on the cloud and if it is not found , then encrypted image is stored on the cloud and else it is not stored.
This way, cloud will not be able to find what the file is because it is encrypted and  no duplicate file will also stored on file .
User can download the image if he/she wants .
