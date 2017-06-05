import { CleintViewPage } from './app.po';

describe('cleint-view App', () => {
  let page: CleintViewPage;

  beforeEach(() => {
    page = new CleintViewPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
