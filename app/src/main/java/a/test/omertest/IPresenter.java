package a.test.omertest;

public interface IPresenter {

    void onItemClick(int adapterPosition);

    void attachView(IView view);

    void detachView();

    void refreshLayoutPulled();

}