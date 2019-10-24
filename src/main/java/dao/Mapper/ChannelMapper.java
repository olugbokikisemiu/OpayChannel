package dao.Mapper;

import dao.model.ChannelJournal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface ChannelMapper {
    @Insert("insert into channeljournal(transactionID, raw_request, raw_response) values(#{transactionID},#{raw_request},#{raw_response})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertJournal(ChannelJournal channel);
}
