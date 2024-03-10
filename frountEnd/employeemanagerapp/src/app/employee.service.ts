import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiServeUrl =environment.apiBaseUrl;
  constructor(private http :HttpClient) { }

  public getEmployees() : Observable<Employee[]>{
    return this.http.get<Employee[]>(`${this.apiServeUrl}/employee/all`);
  }

  public addEmployee(employee :Employee) : Observable<Employee >{
    return this.http.post<Employee>(`${this.apiServeUrl}/employee/add`,employee);
  }

  public updateEmployee(employee :Employee | null) : Observable<Employee >{
    return this.http.put<Employee>(`${this.apiServeUrl}/employee/update`,employee);
  }

  public deleteEmployee(employeeId :number | undefined ) : Observable<void >{
    return this.http.delete<void>(`${this.apiServeUrl}/employee/delete/${employeeId}`);
  }
}
