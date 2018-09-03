package com.bignerdranch.android.reader.state;

public class StateManager {

    private static StateManager mStateManager;

    private StateManager(){}
    public static class StateApp {
        private static String stateFragment;
        private static boolean MainActivityDestroy = false;
        public static String getStateFragment() {
            return stateFragment;
        }

        public static void setStateFragment(String stateFragment) {
            StateApp.stateFragment = stateFragment;
        }

        public static boolean isMainActivityDestroy() {
            return MainActivityDestroy;
        }

        public static void setMainActivityDestroy(boolean mainActivityDestroy) {
            MainActivityDestroy = mainActivityDestroy;
        }
    }

    public static StateManager createStateManager() {
        if (mStateManager == null) {
            mStateManager = new StateManager();
            return mStateManager;
        }else return null;
    }
}

