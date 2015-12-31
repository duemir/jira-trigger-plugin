package com.ceilfors.jenkins.plugins.jirabuilder

import groovy.transform.PackageScope
import hudson.Extension
import hudson.util.Secret
import jenkins.model.GlobalConfiguration
import net.sf.json.JSONObject
import org.kohsuke.stapler.StaplerRequest
/**
 * @author ceilfors
 */
@Extension
class JiraBuilderGlobalConfiguration extends GlobalConfiguration {

    private String jiraRootUrl
    private String jiraUsername
    private Secret jiraPassword

    JiraBuilderGlobalConfiguration() {
        load()
    }

    JiraBuilderGlobalConfiguration(String jiraRootUrl, String jiraUsername, String jiraPassword) {
        setJiraRootUrl(jiraRootUrl)
        setJiraUsername(jiraUsername)
        setJiraPassword(jiraPassword)
    }

    @Override
    boolean configure(StaplerRequest req, JSONObject formData) {
        setJiraRootUrl(formData.getString("jiraRootUrl"))
        setJiraUsername(formData.getString("jiraUsername"))
        setJiraPassword(formData.getString("jiraPassword"))
        save();
        return super.configure(req, formData)
    }

    String getRootUrl() {
        return jiraRootUrl
    }

    String getUsername() {
        return jiraUsername
    }

    Secret getPassword() {
        return jiraPassword
    }

    @PackageScope
    void setJiraRootUrl(String jiraRootUrl) {
        this.jiraRootUrl = jiraRootUrl
    }

    @PackageScope
    void setJiraUsername(String jiraUsername) {
        this.jiraUsername = jiraUsername
    }

    @PackageScope
    void setJiraPassword(String jiraPassword) {
        this.jiraPassword = Secret.fromString(jiraPassword)
    }
}
