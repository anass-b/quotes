import {Pipe, PipeTransform} from '@angular/core';
import { truncate } from './Truncate';

@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {
  transform(value: string, args: number) {
    return truncate(value, args);
  }
}
