package View;

import model.ModelData;

public interface View {
    default void refresh(){ };
    default void refresh(ModelData modelData){ };
}
