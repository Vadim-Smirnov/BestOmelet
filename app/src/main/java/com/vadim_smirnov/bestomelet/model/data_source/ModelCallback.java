package com.vadim_smirnov.bestomelet.model.data_source;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public interface ModelCallback <T> {

    void completion(T response);

    void error(String error);

}
