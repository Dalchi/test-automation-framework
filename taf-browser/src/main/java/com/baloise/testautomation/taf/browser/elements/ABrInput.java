/*
 ===========================================================================
 @    $Author$
 @  $Revision$
 @      $Date$
 @
 ===========================================================================
 */
package com.baloise.testautomation.taf.browser.elements;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.baloise.testautomation.taf.base._base.AInput;
import com.baloise.testautomation.taf.base._interfaces.IAnnotations.Check;
import com.baloise.testautomation.taf.common.interfaces.IFinder;

/**
 * 
 */
public abstract class ABrInput extends AInput {

  private static final int MAX_RETRIES = 5;

  public void checkCustom() {
    Assert.fail("need to subclass and override 'checkCustom':" + getClass());
  }
  
  public void fillCustom() {
    Assert.fail("need to subclass and override 'fillCustom':" + getClass());
  }
  
  public void check() {
    if (checkValue != null) {
      if (checkValue.isCustom()) {
        checkCustom();
        return;
      }
      if (!checkValue.isSkip() && checkValue.isNotNull()) {
        String text = null;
        try {
          int timeout = new Double(((Check)check).timeout() * 1000).intValue();
          if (timeout > 0) {
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() < time + timeout) {
              // If element is an input field, then value is needed
              String tempText = find().getAttribute("value");
              // maybe it's just another element (e.g. a <div>) --> try simple getText()
              if (tempText == null) {
                tempText = find().getText();
              }
              if (checkValue.asString().equals(tempText)) {
                text = tempText;
                break;
              }
            }
          }
        }
        catch (Exception e) {}
        // find for non-timeout-case --> if it's an input --> value is needed
        if (text == null) {
          text = find().getAttribute("value");
        }
        // not found? Maybe it's another element --> try simple getText()
        if (text == null) {
          text = find().getText();
        }
        check(checkValue.asString(), text);
      }
    }
  }

  protected void check(String expected, String actual) {
    assertEquals("value does NOT match for element '" + name + "': ", expected, actual);
  }

  @Override
  public void click() {
    find().click();
  }

  public void fill() {
    if (fillValue != null) {
      if (fillValue.isCustom()) {
        fillCustom();
        return;
      }
      if (!fillValue.isSkip() && fillValue.isNotNull()) {
        find().click();
        find().clear();
        if (getDriver() instanceof InternetExplorerDriver) {
          fillIE();
        }
        else {
          find().sendKeys(fillValueAsString());
          find().sendKeys(Keys.TAB);
        }
      }
    }
  }

  private void fillIE() {
    // TODO
    // could be optimized, but hoping for IEDriver to be fixed
    // so a simple implementation will do for the moment
    if (fillValueAsString().contains("@")) {
      for (Character c : fillValueAsString().toCharArray()) {
        Character character = new Character(c);
        WebElement we = find();
        if (character.equals('@')) {
          we.sendKeys(Keys.chord(Keys.ALT, Keys.CONTROL, "2"));
        }
        else {
          we.sendKeys(character.toString());
        }
      }
    }
    else {
      find().sendKeys(fillValueAsString());
    }
    find().sendKeys(Keys.TAB);
  }

  public WebDriver getDriver() {
    IFinder<?> finder = getFinder();
    if (finder instanceof BrFinder) {
      return ((BrFinder)finder).getDriver();
    }
    return null;
  }

  // @Override
  public WebElement find() {
    return (WebElement)brFind();
  }

}
