package edu.wisc.my.messages.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message
 */


public class Message {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("titleShort")
  private String titleShort = null;

  @JsonProperty("titleUrl")
  private String titleUrl = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("descriptionShort")
  private String descriptionShort = null;

  @JsonProperty("messageType")
  private String messageType = null;

  @JsonProperty("goLiveDate")
  private String goLiveDate = null;

  @JsonProperty("expireDate")
  private String expireDate = null;

  @JsonProperty("featureImageUrl")
  private String featureImageUrl = null;

  @JsonProperty("priority")
  private String priority = null;

  @JsonProperty("recurrence")
  private String recurrence = null;

  @JsonProperty("dismissible")
  private String dismissible = null;

  @JsonProperty("audienceFilter")
  private AudienceFilter audienceFilter = null;

  @JsonProperty("data")
  private Data data = null;

  @JsonProperty("actionButton")
  private ActionButton actionButton = null;

  @JsonProperty("moreInfoButton")
  private ActionButton moreInfoButton = null;

  @JsonProperty("confirmButton")
  private ActionButton confirmButton = null;

  public Message id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   **/
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Message title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   *
   * @return title
   **/
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Message titleShort(String titleShort) {
    this.titleShort = titleShort;
    return this;
  }

  public String getTitleShort() {
    return titleShort;
  }

  public void setTitleShort(String titleShort) {
    this.titleShort = titleShort;
  }

  public String getTitleUrl() {
    return titleUrl;
  }

  public void setTitleUrl(String titleUrl) {
    this.titleUrl = titleUrl;
  }

  public Message description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Message descriptionShort(String descriptionShort) {
    this.descriptionShort = descriptionShort;
    return this;
  }

  public String getDescriptionShort() {
    return descriptionShort;
  }

  public void setDescriptionShort(String descriptionShort) {
    this.descriptionShort = descriptionShort;
  }

  public Message messageType(String messageType) {
    this.messageType = messageType;
    return this;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public Message goLiveDate(String goLiveDate) {
    this.goLiveDate = goLiveDate;
    return this;
  }

  public String getGoLiveDate() {
    return goLiveDate;
  }

  public void setGoLiveDate(String goLiveDate) {
    this.goLiveDate = goLiveDate;
  }

  public Message expireDate(String expireDate) {
    this.expireDate = expireDate;
    return this;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }

  public Message featureImageUrl(String featureImageUrl) {
    this.featureImageUrl = featureImageUrl;
    return this;
  }

  public String getFeatureImageUrl() {
    return featureImageUrl;
  }

  public void setFeatureImageUrl(String featureImageUrl) {
    this.featureImageUrl = featureImageUrl;
  }

  public Message priority(String priority) {
    this.priority = priority;
    return this;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public Message recurrence(String recurrence) {
    this.recurrence = recurrence;
    return this;
  }

  public String getRecurrence() {
    return recurrence;
  }

  public void setRecurrence(String recurrence) {
    this.recurrence = recurrence;
  }

  public Message dismissible(String dismissible) {
    this.dismissible = dismissible;
    return this;
  }

  public String getDismissible() {
    return dismissible;
  }

  public void setDismissible(String dismissible) {
    this.dismissible = dismissible;
  }

  public Message audienceFilter(AudienceFilter audienceFilter) {
    this.audienceFilter = audienceFilter;
    return this;
  }

  public AudienceFilter getAudienceFilter() {
    return audienceFilter;
  }

  public void setAudienceFilter(AudienceFilter audienceFilter) {
    this.audienceFilter = audienceFilter;
  }

  public Message data(Data data) {
    this.data = data;
    return this;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public Message actionButton(ActionButton actionButton) {
    this.actionButton = actionButton;
    return this;
  }

  public ActionButton getActionButton() {
    return actionButton;
  }

  public void setActionButton(ActionButton actionButton) {
    this.actionButton = actionButton;
  }

  public Message moreInfoButton(ActionButton moreInfoButton) {
    this.moreInfoButton = moreInfoButton;
    return this;
  }

  public ActionButton getMoreInfoButton() {
    return moreInfoButton;
  }

  public void setMoreInfoButton(ActionButton moreInfoButton) {
    this.moreInfoButton = moreInfoButton;
  }

  public Message confirmButton(ActionButton confirmButton) {
    this.confirmButton = confirmButton;
    return this;
  }

  public ActionButton getConfirmButton() {
    return confirmButton;
  }

  public void setConfirmButton(ActionButton confirmButton) {
    this.confirmButton = confirmButton;
  }

  @JsonIgnore
  public boolean isValidToday(){

   boolean isValidToday = false;
   Date today = new Date();
   
   // Placeholder date format string, 
   // can be overridden with an application property.
   String formatString = "yyyy-mm-dd";

   try{ 
   //If there's no date specified, the message is valid.
   if(StringUtils.isBlank(getGoLiveDate()) && 
     StringUtils.isBlank(getExpireDate())) {
       return true;
     }

  SimpleDateFormat defaultFormatter = new SimpleDateFormat(formatString);

  String goLive = getGoLiveDate();
  String expiration = getExpireDate();
  
  // If the message has no goLiveDate, we presume it to have been valid 
  // since the beginning of time. Or 1977. Whichever came first.
  String startDateString= (StringUtils.isNotBlank(goLive)) ? 
       goLive : "1977-08-16";

  // If the message has no expirationDate, we presume it will be valid until 
  // the end of time. Or until we have a Y4K problem. Whichever comes first.       
  String endDateString= (StringUtils.isNotBlank(expiration)) ?
       expiration : "3999-12-31";

   Date startDate = defaultFormatter.parse(startDateString);
   Date endDate = defaultFormatter.parse(endDateString);

    if(today.after(startDate) && today.before(endDate)) {
      isValidToday = true;
    }
   } catch (Exception e) {
     logger.warn("DATE ERROR " + this.id + " " + e.getMessage());
     isValidToday =  false;
   }

   return isValidToday;
 }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.id, message.id) &&
      Objects.equals(this.title, message.title) &&
      Objects.equals(this.titleShort, message.titleShort) &&
      Objects.equals(this.description, message.description) &&
      Objects.equals(this.descriptionShort, message.descriptionShort) &&
      Objects.equals(this.titleUrl, message.titleUrl) &&
      Objects.equals(this.messageType, message.messageType) &&
      Objects.equals(this.goLiveDate, message.goLiveDate) &&
      Objects.equals(this.expireDate, message.expireDate) &&
      Objects.equals(this.featureImageUrl, message.featureImageUrl) &&
      Objects.equals(this.priority, message.priority) &&
      Objects.equals(this.recurrence, message.recurrence) &&
      Objects.equals(this.dismissible, message.dismissible) &&
      Objects.equals(this.audienceFilter, message.audienceFilter) &&
      Objects.equals(this.data, message.data) &&
      Objects.equals(this.actionButton, message.actionButton) &&
      Objects.equals(this.moreInfoButton, message.moreInfoButton) &&
      Objects.equals(this.confirmButton, message.confirmButton);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(id, title, titleShort, description, descriptionShort, titleUrl, messageType, goLiveDate,
        expireDate, featureImageUrl, priority, recurrence, dismissible, audienceFilter, data,
        actionButton, moreInfoButton, confirmButton);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    titleShort: ").append(toIndentedString(titleShort)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    descriptionShort: ").append(toIndentedString(descriptionShort)).append("\n");
    sb.append("    titleUrl: ").append(toIndentedString(titleUrl)).append("\n");
    sb.append("    messageType: ").append(toIndentedString(messageType)).append("\n");
    sb.append("    goLiveDate: ").append(toIndentedString(goLiveDate)).append("\n");
    sb.append("    expireDate: ").append(toIndentedString(expireDate)).append("\n");
    sb.append("    featureImageUrl: ").append(toIndentedString(featureImageUrl)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    recurrence: ").append(toIndentedString(recurrence)).append("\n");
    sb.append("    dismissible: ").append(toIndentedString(dismissible)).append("\n");
    sb.append("    audienceFilter: ").append(toIndentedString(audienceFilter)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    actionButton: ").append(toIndentedString(actionButton)).append("\n");
    sb.append("    moreInfoButton: ").append(toIndentedString(moreInfoButton)).append("\n");
    sb.append("    confirmButton: ").append(toIndentedString(confirmButton)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
