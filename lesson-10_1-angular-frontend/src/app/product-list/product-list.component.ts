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
    this.findAll(1);
  }

  public delete(id: number | null) {
    this.productService.delete(id).subscribe( {
      next: () => {
        console.info(`Product with id = ${id} was successfully deleted`);
        this.findAll(1);
      },
      error: (msg: String) => {
        console.error(msg);
      }
    })
  }

  private findAll(page: number) {
    this.productService.findAll(page)
      .subscribe({
        next: (res: Page) => {
          this.products = res.content;
        },
        error: (msg: String) => {
          console.error(msg);
        }
      })
  }
}
