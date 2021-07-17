package com.luckyaf.smartandroid.domain.usecase;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/17
 */
public interface UseCaseScheduler {
    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(
            final UseCase.UseCaseCallback<V> useCaseCallback);
}
