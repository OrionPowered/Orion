package pro.prysm.orion.api.protocol.status;

import lombok.Data;

@Data
public class PlayersResponse {
    private int max;
    private int online;
    private SampleResponse[] sample;
}