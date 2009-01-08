package it.assist.jrecordbind;

import it.assist.jrecordbind.RecordDefinition.Property;

import java.util.List;

/**
 * Padders used in a fixed-length file definition are instanciated and cached
 * here
 * 
 * @author Federico Fissore
 */
class PaddersCache extends Cache<Padder> {

  public PaddersCache(RecordDefinition definition) {
    List<Property> properties = collectProperties(definition);
    for (Property property : properties) {
      if (property.getPadder() != null && !containsKey(property.getPadder())) {
        try {
          put(property.getPadder(), (Padder) Class.forName(property.getPadder()).newInstance());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

}
