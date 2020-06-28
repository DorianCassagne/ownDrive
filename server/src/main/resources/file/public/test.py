import smbclient

# Optional - register the credentials with a server
smbclient.register_session("server", username="user", password="pass")

smbclient.mkdir(r"\\server\share\directory", username="user", password="pass")

with smbclient.open_file(r"\\server\share\directory\file.txt", mode="w") as fd:
    fd.write(u"file contents")