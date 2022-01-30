import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll(page:number) {
    return this.http.get<Page>(`api/v1/product?page=${page}`)
  }

  public findById(id: number) {
    return this.http.get<Product>(`api/v1/product/${id}`);
  }

  public save(product: Product) {
    if (product.id != null) {
      return this.http.put<Product>(`api/v1/product`, product);
    }
    return this.http.post<Product>(`api/v1/product`, product);
  }

  public delete(id: number | null) {
    return this.http.delete(`api/v1/product/${id}`);
  }
}
