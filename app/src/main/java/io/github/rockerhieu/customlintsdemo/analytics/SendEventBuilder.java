/*
 * Copyright (C) 2016 Trust Circle Global. All rights reserved.
 *
 * This software is the confidential and proprietary information of Trust Circle Global or one of its subsidiaries.
 * You shall not disclose this confidential information and shall use it only in accordance with the terms of the
 * license agreement or other applicable agreement you entered into with Trust Circle Global.
 *
 * TRUST CIRCLE GLOBAL MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 * OR NON-INFRINGEMENT. TRUST CIRCLE GLOBAL SHALL NOT BE LIABLE FOR ANY LOSSES OR DAMAGES SUFFERED BY LICENSEE AS A
 * RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package io.github.rockerhieu.customlintsdemo.analytics;

public class SendEventBuilder {
  private String screen;
  private String category;
  private String action;
  private String label;
  private Long value;

  public SendEventBuilder() {
  }

  public SendEventBuilder screen(String screen) {
    this.screen = screen;
    return this;
  }

  public SendEventBuilder category(String category) {
    this.category = category;
    return this;
  }

  public SendEventBuilder action(String action) {
    this.action = action;
    return this;
  }

  public SendEventBuilder label(String label) {
    this.label = label;
    return this;
  }

  public SendEventBuilder value(Long value) {
    this.value = value;
    return this;
  }

  public void send() {
    // swallow
  }
}