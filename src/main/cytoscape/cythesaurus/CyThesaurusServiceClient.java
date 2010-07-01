package cytoscape.cythesaurus;

import java.util.Map;
import java.util.Set;

public abstract interface CyThesaurusServiceClient
{
  public abstract boolean isServiceAvailable();

  public abstract double serviceVersion();

  public abstract boolean openAttributeConfigDialog();

  public abstract boolean openMappingResourceConfigDialog();

  public abstract Set<String> allIDMappers();

  public abstract Set<String> selectedIDMappers();

  public abstract boolean registerIDMapper(String paramString1, String paramString2, String paramString3);

  public abstract boolean unregisterIDMapper(String paramString);

  public abstract boolean setIDMapperSelect(String paramString, boolean paramBoolean);

  public abstract Set<String> supportedSrcIDTypes();

  public abstract Set<String> supportedTgtIDTypes();

  public abstract boolean isMappingSupported(String paramString1, String paramString2);

  public abstract boolean mapID(Set<String> paramSet1, String paramString1, String paramString2, Set<String> paramSet2, String paramString3);

  public abstract Map<String, String> mapID(Set<String> paramSet1, String paramString, Set<String> paramSet2, Set<String> paramSet3);

  public abstract Map<String, Set<String>> mapID(Set<String> paramSet, String paramString1, String paramString2);

  public abstract boolean idExists(String paramString1, String paramString2);
}