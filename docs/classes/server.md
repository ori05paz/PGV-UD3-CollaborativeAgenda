# Server

The Server class is responsible for managing multiple client connections.  
It runs on a specific port and handles incoming connections in a multi-threaded environment.  
<br><br>

## Methods

#### - main(String[] args):

&nbsp;&nbsp;&nbsp;&nbsp;
Starts the server and listens for incoming client connections.

#### - acceptClient(Socket socket):

&nbsp;&nbsp;&nbsp;&nbsp;
Accepts new client connections and assigns them to a ClientHandler instance.

<br><br>

[← Back to Documentation](documentation.md)
