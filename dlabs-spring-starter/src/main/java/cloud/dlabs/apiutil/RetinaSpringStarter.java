package cloud.dlabs.apiutil;

import cloud.dlabs.networkutils.Retina;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
public abstract class RetinaSpringStarter<T> extends Retina<T> {

    @PostConstruct
    public void onInit() {
        this.init();
    }
}
