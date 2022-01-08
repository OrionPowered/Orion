package pro.prysm.orion.api.protocol.status;

import lombok.Data;
import lombok.Getter;

@Data
public class VersionResponse {
    private String name;
    private int protocol;
}
