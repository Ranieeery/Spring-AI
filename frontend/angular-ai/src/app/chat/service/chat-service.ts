import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ChatResponse } from '../response/chat-response';

@Injectable({
  providedIn: 'root',
})
export class ChatService {

  private readonly API = '/api/chat/chat-memory';
  private http = inject(HttpClient);

  sendMessage(message: string) {
    return this.http.post<ChatResponse>(this.API, { message: message });
  }
}
