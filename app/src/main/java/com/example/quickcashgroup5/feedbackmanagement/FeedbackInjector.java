package com.example.quickcashgroup5.feedbackmanagement;

/**
 * Class to inject Feedback to other classes
 */
public class FeedbackInjector {
    private static FeedbackInjector instance = null;
    private final IFeedbackAbstractFactory feedbackAbstractFactory;

    /**
     * Constructor for the injector
     */
    private FeedbackInjector() {
        feedbackAbstractFactory = new FeedbackAbstractFactory();
    }

    /**
     * Gets the instance of the FeedbackInjector
     *
     * @return
     */
    public static FeedbackInjector getInstance() {
        if (instance == null) {
            instance = new FeedbackInjector();
        }
        return instance;
    }

    /**
     * Gets the abstact interface of Feedback
     *
     * @return
     */
    public IFeedbackAbstractFactory getFeedbackAbstractFactory() {
        return feedbackAbstractFactory;
    }
}
