export function truncate(text: string, maxLength: number): string {
  if (maxLength === undefined || maxLength === 0) {
    return text;
  }

  if (text.length > maxLength) {
    return text.substring(0, maxLength) + '...';
  } else {
    return text;
  }
}