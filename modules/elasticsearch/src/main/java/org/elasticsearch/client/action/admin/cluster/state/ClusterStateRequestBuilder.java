/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.client.action.admin.cluster.state;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.state.ClusterStateRequest;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.action.admin.cluster.support.BaseClusterRequestBuilder;
import org.elasticsearch.common.unit.TimeValue;

/**
 * @author kimchy (shay.banon)
 */
public class ClusterStateRequestBuilder extends BaseClusterRequestBuilder<ClusterStateRequest, ClusterStateResponse> {

    public ClusterStateRequestBuilder(ClusterAdminClient clusterClient) {
        super(clusterClient, new ClusterStateRequest());
    }

    /**
     * Should the cluster state result include the {@link org.elasticsearch.cluster.metadata.MetaData}. Defaults
     * to <tt>false</tt>.
     */
    public ClusterStateRequestBuilder setFilterMetaData(boolean filter) {
        request.filterMetaData(filter);
        return this;
    }

    /**
     * Should the cluster state result include the {@link org.elasticsearch.cluster.node.DiscoveryNodes}. Defaults
     * to <tt>false</tt>.
     */
    public ClusterStateRequestBuilder setFilterNodes(boolean filter) {
        request.filterNodes(filter);
        return this;
    }

    /**
     * Should the cluster state result include teh {@link org.elasticsearch.cluster.routing.RoutingTable}. Defaults
     * to <tt>false</tt>.
     */
    public ClusterStateRequestBuilder setFilterRoutingTable(boolean filter) {
        request.filterRoutingTable(filter);
        return this;
    }

    /**
     * When {@link #setFilterMetaData(boolean)} is not set, which indices to return the {@link org.elasticsearch.cluster.metadata.IndexMetaData}
     * for. Defaults to all indices.
     */
    public ClusterStateRequestBuilder setFilterIndices(String... indices) {
        request.filteredIndices(indices);
        return this;
    }

    /**
     * Sets the master node timeout in case the master has not yet been discovered.
     */
    public ClusterStateRequestBuilder setMasterNodeTimeout(TimeValue timeout) {
        request.masterNodeTimeout(timeout);
        return this;
    }

    /**
     * Sets the master node timeout in case the master has not yet been discovered.
     */
    public ClusterStateRequestBuilder setMasterNodeTimeout(String timeout) {
        request.masterNodeTimeout(timeout);
        return this;
    }

    /**
     * Sets if the cluster state request should be executed locally on the node, and not go to the master.
     */
    public ClusterStateRequestBuilder setLocal(boolean local) {
        request.local(local);
        return this;
    }

    @Override protected void doExecute(ActionListener<ClusterStateResponse> listener) {
        client.state(request, listener);
    }
}
