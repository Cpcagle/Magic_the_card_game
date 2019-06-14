@author Cameron Cagle
@version 11/6/18

This program is an application that implements the client-side and the server
side of a new protocol the 'magic protocol. The application will support both
the Transmission Control Protocol and the User Datagram Protocol versions of 
protocol.

How to compile: while at package level enter "javac */*.java

How to run Udp: First on one agora server type "java Server.MagicServerDriver 
            udp (Optional: port number).

    Second on a seperate agora server type "java Client.MagicClientDriver udp 
            (localhost or IPaddress) (Optional: port number or flag) 
            (Optional: flag).

Package Client************

MagicClientDriver: runs the UDP or TCP protocol that connects to your server

MagicClient: is an interface to the magic client implementation.

AbstractMagicClient: hold attributes that are common to all magic client
    implementations, as well as protected methods that enable subclasses to 
    access those attributes.

MagicTcpClient: sends and receives data using a Transmission Control Protocol.

MagicUdpClient: sends and receives data using a User Datagram Protocol.

Package Common*************

Card: Class represents simple cards in the game of magic the gathering.

Type: Enumeration for allowed Card type in magic the gathering.

Package Server*************

MagicServerDriver: This calls contains an applicaton that can drive both TCP
    and UDP implementations of a MagicServer.

MagicServer: the interface to a magic server.

CardSource: Class that define the type of cards that can be returned fo a deck
    in Magic the Gathering.

AbstractMagicServer: an abstract class that contains fields and methods that
    may be common to implementations of the chargen server

TcpMagicServer: This class represents a concrete implementation of a magic 
    server that uses the TCP transport layer protocol.

UdpMagicServer: This class represents a concrete implementation of a magic 
    server that uses the UDP transport layer protocol.
  
CardType: Simple Enumeration for Card types. 
