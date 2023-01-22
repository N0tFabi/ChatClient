# ChatClient
A Chatclient made in Java to chat via cmd with eachother in a local network
<h1 align="center">ChatClient</h1>
A simple local Chat Client made in Java.

# Features
```java
You can choose your name and chat with that name on a local Chat Server
```

# How the code works:

## ChatServer
This server uses a ServerSocket to listen for incoming connections on port 8000. When a new client connects, it creates a new ClientHandler object to handle the client, adds it to a list of connected clients, and starts a new thread to handle the client. The ClientHandler class reads the client's name, sends a message to all connected clients to inform them of the new client, and continuously reads messages from the client and sends them to all connected clients. If a client disconnects, the ClientHandler removes the client from the list of connected clients and sends a message to all connected clients to inform them of the client disconnection.

## ChatClient
The program prompts the user to enter their name, then creates input and output streams to communicate with the server. The program then sends the user's name to the server and creates a new thread to continuously read messages from the server. The main thread of the program then continuously reads messages from the user and sends them to the server. The ServerMessageHandler class creates a new thread that reads incoming messages from the server and prints them to the console. If an IOException occurs, it will be caught and printed out to the console.

## Error/Fixes
> When finding an Error feel free to contact me: NotFabi#3973
