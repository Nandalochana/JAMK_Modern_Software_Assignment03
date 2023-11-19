package pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewInfo {

    private int showType; // 1- barber / 2-customer else all


    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }
}
