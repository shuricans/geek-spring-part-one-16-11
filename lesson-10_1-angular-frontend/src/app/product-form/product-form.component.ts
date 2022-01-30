import { Component, OnInit } from '@angular/core';
import {ProductService} from "../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.less']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", "", -1, "", 0);

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param['id'] == 'new') {
        this.product = new Product(-1, "", "", -1, "", 0);
      } else {
        this.productService.findById(param['id'])
          .subscribe({
            next: (p: Product) => {
              this.product = p;
            },
            error: (msg: String) => {
              console.error(msg);
            }
          })
      }
    })
  }

  save() {
    this.productService.save(this.product)
      .subscribe( {
        next: (p: Product) => {
          console.info(p);
          this.router.navigateByUrl('/product');
        },
        error: (msg: String) => {
          console.error(msg);
        }
      })
  }
}
