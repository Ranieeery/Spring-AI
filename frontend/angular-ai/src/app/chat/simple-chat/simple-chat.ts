import { Component, ElementRef, signal, ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-simple-chat',
  imports: [
    MatCardModule,
    MatToolbarModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    NgClass,
  ],
  templateUrl: './simple-chat.html',
  styleUrl: './simple-chat.scss',
})
export class SimpleChat {

  @ViewChild('chatHistory')
  private chatHistory!: ElementRef;

  userPrompt = '';
  isLoading = false;

  messages = signal([{ text: 'Hello, how can I help you?', isBot: true }]);
  sendMessage() {
    this.trimUserPrompt();

    if (this.userPrompt.length !== 0 && !this.isLoading) {
      this.updateMessages(this.userPrompt);
      this.isLoading = true;
      this.userPrompt = '';
      this.simulateBotResponse();
    }
  }

  private trimUserPrompt() {
    this.userPrompt = this.userPrompt.trim();
  }
  private updateMessages(text: string, isBot = false) {
    this.messages.update((messages) => [
      ...messages,
      { text: text, isBot: isBot },
    ]);
    this.scrollToBottom();
  }

  private simulateBotResponse() {
    setTimeout(() => {
      const response = 'This is a simulated response from the bot.';
      this.updateMessages(response, true);
      this.isLoading = false;
    }, 2000);
  }

  private scrollToBottom() {
    try {
      this.chatHistory.nativeElement.scrollTop =
        this.chatHistory.nativeElement.scrollHeight;
    } catch (error) {
      console.error('Error scrolling to bottom:', error);
    }
  }
}
