import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import * as Stomp from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import {MyRestService} from "../services/my-rest.service";


@Component({
  selector: 'app-chat-app',
  templateUrl: './chat-app.component.html',
  styleUrls: ['./chat-app.component.css']
})
export class ChatAppComponent {
  // usernameForm: FormGroup;
  // messageForm: FormGroup;
  // username: string | null = null;
  // connecting = true;
  // messages: any[] = [];
  //
  // private stompClient: Stomp.Client | null = null;
  //
  // constructor(private formBuilder: FormBuilder) {
  //   this.usernameForm = this.formBuilder.group({
  //     username: ['']
  //   });
  //
  //   this.messageForm = this.formBuilder.group({
  //     message: ['']
  //   });
  // }
  //
  // ngOnInit() {
  //   // Connect to the WebSocket server
  //   this.connectWebSocket();
  // }
  // connectWebSocket() {
  //   const socket = new SockJS('/ws');
  //   this.stompClient = Stomp.Stomp.over(socket);
  //
  //   this.stompClient.configure({
  //     onConnect: () => {
  //       this.connecting = false;
  //       this.subscribeToChat();
  //     }
  //   });
  //
  //   this.stompClient.activate();
  // }
  //
  // subscribeToChat() {
  //   if (this.stompClient) {
  //     this.stompClient.subscribe('/topic/public', (payload: Stomp.Message) => {
  //       const message = JSON.parse(payload.body);
  //       this.messages.push(message);
  //     });
  //   }
  // }
  //
  // connect() {
  //   this.username = this.usernameForm.get('username')?.value.trim();
  //
  //   if (this.username) {
  //     this.usernameForm.disable(); // Disable the username input field after connecting
  //
  //     // Send a JOIN message to the server
  //     const joinMessage = {
  //       sender: this.username,
  //       type: 'JOIN'
  //     };
  //
  //     this.sendMessageToServer(joinMessage);
  //   }
  // }
  //
  // sendMessage() {
  //   const messageContent = this.messageForm.get('message')?.value.trim();
  //
  //   if (messageContent) {
  //     // Send a CHAT message to the server
  //     const chatMessage = {
  //       sender: this.username,
  //       content: messageContent,
  //       type: 'CHAT'
  //     };
  //
  //     this.sendMessageToServer(chatMessage);
  //
  //     // Clear the message input field
  //     this.messageForm.get('message')?.setValue('');
  //   }
  // }
  //
  // sendMessageToServer(message: any) {
  //   if (this.stompClient) {
  //     this.stompClient.publish({
  //       destination: '/app/chat.sendMessage',
  //       body: JSON.stringify(message)
  //     });
  //   }
  // }
  //
  // getAvatarColor(messageSender: string) {
  //   let hash = 0;
  //   for (let i = 0; i < messageSender.length; i++) {
  //     hash = 31 * hash + messageSender.charCodeAt(i);
  //   }
  //
  //   const index = Math.abs(hash % 8); // Use the length of your colors array
  //   const colors = [
  //     '#2196F3', '#32c787', '#00BCD4', '#ff5652',
  //     '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
  //   ];
  //
  //   return colors[index];
  // }


  // ngOnInit(): void {
  //   // this.rs.receiveMessages().subscribe((message: string) => {
  //   //   this.messages.push(message);
  //   // });
  //   this.rs.connectToWebSocket();
  // }

  // sendMessage() {
  //   if (this.message) {
  //     this.rs.sendMessage(this.message);
  //     this.message = '';
  //   }
  // }
}
