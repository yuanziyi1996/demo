package com.yzy.heatmap.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    public String id;
    public String province;
    public String shiji;
    public String quxian;
    public String zhudi;
    public double renkou;
    public String area;
    public String code;
    public String quhao;
    public String youbian;
    public String lng;
    public String lat;
}
