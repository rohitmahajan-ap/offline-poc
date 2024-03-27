package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Asset type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Assets")
public final class Asset implements Model {
  public static final QueryField ID = field("Asset", "id");
  public static final QueryField CATEGORY = field("Asset", "category");
  public static final QueryField DESCRIPTION = field("Asset", "description");
  public static final QueryField NAME = field("Asset", "name");
  private final @ModelField(targetType="String", isRequired = true) String id;
  private final @ModelField(targetType="String") String category;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="String") String name;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getCategory() {
      return category;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getName() {
      return name;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Asset(String id, String category, String description, String name) {
    this.id = id;
    this.category = category;
    this.description = description;
    this.name = name;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Asset asset = (Asset) obj;
      return ObjectsCompat.equals(getId(), asset.getId()) &&
              ObjectsCompat.equals(getCategory(), asset.getCategory()) &&
              ObjectsCompat.equals(getDescription(), asset.getDescription()) &&
              ObjectsCompat.equals(getName(), asset.getName()) &&
              ObjectsCompat.equals(getCreatedAt(), asset.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), asset.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getCategory())
      .append(getDescription())
      .append(getName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Asset {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("category=" + String.valueOf(getCategory()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Asset justId(String id) {
    return new Asset(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      category,
      description,
      name);
  }
  public interface BuildStep {
    Asset build();
    BuildStep id(String id);
    BuildStep category(String category);
    BuildStep description(String description);
    BuildStep name(String name);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String category;
    private String description;
    private String name;
    @Override
     public Asset build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Asset(
          id,
          category,
          description,
          name);
    }
    
    @Override
     public BuildStep category(String category) {
        this.category = category;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String category, String description, String name) {
      super.id(id);
      super.category(category)
        .description(description)
        .name(name);
    }
    
    @Override
     public CopyOfBuilder category(String category) {
      return (CopyOfBuilder) super.category(category);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
  }
  
}
