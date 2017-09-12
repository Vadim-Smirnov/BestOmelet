package com.vadim_smirnov.bestomelet.baseElements;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public interface BaseContract {

    interface View<T> {

        void setPresenter(T presenter);

    }

    interface Presenter {

        void start();

    }

}
