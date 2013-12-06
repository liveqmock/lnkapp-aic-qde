package org.fbi.linking.codec.dataformat.samples.aicmodel.T2000;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;
import org.fbi.linking.codec.dataformat.annotation.OneToMany;
import org.fbi.linking.codec.dataformat.samples.staringmodel.T1000.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhanrui
 */
@FixedLengthTextMessage(mainClass = true)
public class TIA2000 {

    @DataField(seq = 1, length = 2)
    private String id;

    @DataField(seq = 2, length = 4)
    private String name;

    @DataField(seq = 3, length = 2)
    private String itemNum;

    @DataField(seq = 4, length = 6)
    @OneToMany(mappedTo = "org.fbi.linking.codec.dataformat.samples.aicmodel.T2000.Item", totalNumberField = "itemNum")
    private java.util.List<Item> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
