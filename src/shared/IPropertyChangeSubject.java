package shared;

import java.beans.PropertyChangeListener;

public interface IPropertyChangeSubject {

    void addPropertyChangeListener(String name, PropertyChangeListener listener);

}
