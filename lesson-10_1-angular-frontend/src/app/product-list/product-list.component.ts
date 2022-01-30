import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product";
import {Page} from "../model/page";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.less']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.productService.findAll(1)
      .subscribe({
        next: (res: Page) => {
          this.products = res.content;
        },
        error: (msg: String) => {
          console.error(msg);
        }
      })
  }

  public delete(id: number | null) {
    //TODO
  }
}
