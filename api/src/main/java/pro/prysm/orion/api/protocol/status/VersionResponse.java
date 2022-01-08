package pro.prysm.orion.api.protocol.status;

import lombok.Data;
import lombok.Getter;

@Data
public class VersionResponse {
    @Getter
    private String name;
    private int protocol;
}
