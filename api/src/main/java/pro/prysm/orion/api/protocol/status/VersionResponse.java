package pro.prysm.orion.api.protocol.status;

import lombok.Data;

@Data
public class VersionResponse {
    private String name;
    private int protocol;
}
