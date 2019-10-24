# opay-channel-utils

This library consist of methods that will be needed across Opay channel micro-services.

# Packages
* dao
    * Mapper
        * Interface ChannelMapper.java
            * Method int insertJournal(ChannelJournal channel)
            
     * model
        * Class ChannelJournal.java
  
    * Class DataLayer.java
        * Method void PersistJournal(String db, String url, String user, String password, ChannelJournal channel)
    
* http
    * Class OkHttpUtil.java
        * Method String doGetHttpRequest(String url)
        * Method String doFormGetRequest(String url, String req, Map<String,String> mapHeader)
        * Method String doPostHttpRequest(String url, String json)
        * Method String doPostHttpsRequest(String url, String json, Map<String, String> mapHeader)    

* util
    * Common.java  
        * Method String CalculateHash(String hashValue, String hashAlgo)
        * Method String ReadEnv(String envName)


