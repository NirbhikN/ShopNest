import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {

  transform(text: string, lines: number): string {
    const textLines = text.split('\n');
    if (textLines.length > lines) {
      return textLines.slice(0, lines).join('\n');
    }
    return text;
  }

}
