import { AppPage } from './app.po';
import { browser, logging,element,by,By } from 'protractor';
import { Feedback } from '../../src/app/model/feedback';

describe('GuestBook App', () => {
  let page: AppPage;
  let feedback: Feedback;
  let feedbacks:Feedback[];

  beforeEach(() => {
    page = new AppPage();
  });

  it('should have a title', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('GuestbookUi');
  });

  it('should load a page and verify the URL',()=>{
    page.navigateTo();
    expect(browser.getCurrentUrl()).toEqual(browser.baseUrl+'feedback');
  });

  it('check the count of feedbacks', function() {
    expect(element.all(by.css('tr')).count()).toEqual(4);
  });

  it('check created User',()=>{
    page.navigateTo();
    element(by.css('#userName')).sendKeys('harshitha');
    element(by.css('#comment')).sendKeys('good movie');
    element(by.css('#save-button')).click();

    browser.sleep(1000);
    element.all(by.css('table tbody tr')).then(function(items) {
      expect(items[3].getText()).toContain('harshitha');
    });  

  });

  it('check UserLikes',()=>{ 
    page.navigateTo();
    element.all(By.id('likes')).get(1).click();
    element.all(by.css('table tbody tr')).then(function(items){
      expect(items[1].getText()).toContain('1');
     
    })

  });


  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
