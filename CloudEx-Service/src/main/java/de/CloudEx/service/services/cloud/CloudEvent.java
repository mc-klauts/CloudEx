package de.CloudEx.service.services.cloud;

public abstract class CloudEvent {

    private boolean canceled;

    public protected void setCanceled(boolean canceled) {
         this.canceled = canceled;
    }
    
    public protected boolean isCanceled() {
         return canceled;
    }
}
