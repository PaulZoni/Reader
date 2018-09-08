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

    public static class StateReadList {
        private static boolean ReadListDestroy = false;
        private static String TypeFile;
        private static String path;
        private static int pageNumber;

        public static String getPath() {
            return path;
        }

        public static void setPath(String path) {
            StateReadList.path = path;
        }

        public static boolean isReadListDestroy() {
            return ReadListDestroy;
        }

        public static void setReadListDestroy(boolean readListDestroy) {
            ReadListDestroy = readListDestroy;
        }

        public static String getTypeFile() {
            return TypeFile;
        }

        public static void setTypeFile(String typeFile) {
            TypeFile = typeFile;
        }

        public static int getPageNumber() {
            return pageNumber;
        }

        public static void setPageNumber(int pageNumber) {
            StateReadList.pageNumber = pageNumber;
        }
    }

    public static StateManager createStateManager() {
        if (mStateManager == null) {
            mStateManager = new StateManager();
            return mStateManager;
        }else return null;
    }
}

