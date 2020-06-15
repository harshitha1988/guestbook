import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from '../model/feedback';
import { HttpClient } from '@angular/common/http';
import { FeebackComponent } from '../components/feeback/feeback.component';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient) { }

  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>('http://localhost:8080/feedback');
  }
  
  saveFeedback(feedback: Feedback) : Observable<any> {
    return this.http.post('http://localhost:8080/feedback/create', feedback);
  }

  likeFeedback(feedback: Feedback) : Observable<any> {
    return this.http.put('http://localhost:8080/feedback/like/' + feedback.feedbackId, null)
  }

  unlikeFeedback(feedback: Feedback) : Observable<any> {
    return this.http.put('http://localhost:8080/feedback/unlike/' + feedback.feedbackId, null)
  }
}
