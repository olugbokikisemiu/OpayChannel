package dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ChannelJournal {
    private int id;
    private String transactionID;
    private String raw_request;
    private String raw_response;
}
