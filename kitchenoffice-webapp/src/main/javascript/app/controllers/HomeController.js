app.controller('HomeController', function($rootScope, $scope, eventService, flash) {
	
	$scope.doAttend = false;
	$scope.doDismiss = false;
	$scope.doDelete = false;
	$scope.event = null;
	
	$scope.refresh = function() {
		$scope.homeEvents = eventService.getHomeEvents().then(function(events) {
			for ( var i = 0; i < events.length; i++) {
				var event = events[i];
				event.canAttend = $rootScope.containsMe( event.participants );
			}
			return events;
		});
	};
	
	$scope.refresh();

	$scope.areEventsEmpty = $scope.homeEvents.then(function(events) {
		return !(events.length > 0);
	});
	
	$scope.containsArrayObject = function(array, obj) {
		return _.contains(array, obj);
	};
	
	$scope.attendEvent = function(event) {
		$rootScope.processing = true;
		eventService.attendEvent(event).then( function(event) {
			window.scrollTo(0, 0);
			$scope.refresh();
			flash('success', 'You successfully attend event '+eventService.displayName(event)+'.');
			$scope.attendModal.close();
		}, function() {
			$scope.attendModal.close();
		});
	};
	
	$scope.dismissEvent = function(event) {
		$rootScope.processing = true;
		eventService.dismissEvent(event).then( function(event) {
			window.scrollTo(0, 0);
			$scope.refresh();
			flash('success', 'You successfully dismissed event '+eventService.displayName(event)+'.');
			$scope.dismissModal.close();
		}, function() {
			$scope.dismissModal.close();
		});
	};
	
	$scope.deleteEvent = function(event) {
		$rootScope.processing = true;
		eventService.deleteEvent(event).then( function(event) {
			window.scrollTo(0, 0);
			$scope.refresh();
			flash('success', 'You successfully deleted event an event.');
			$scope.deleteModal.close();
		}, function() {
			$scope.deleteModal.close();
		});
	};
	
	$scope.attendModal = {
			opts: {
				backdropFade : true,
				dialogFade : true
			},
			close: function() {
				$scope.doAttend = false;
				$scope.event = null;
			},
			open: function(event) {
				$scope.doAttend = true;
				$scope.event = event;
			}
	};
	
	$scope.dismissModal = {
			opts: {
				backdropFade : true,
				dialogFade : true
			},
			close: function() {
				$scope.doDismiss = false;
				$scope.event = null;
			},
			open: function(event) {
				$scope.doDismiss = true;
				$scope.event = event;
			}
	};
	
	$scope.deleteModal = {
			opts: {
				backdropFade : true,
				dialogFade : true
			},
			close: function() {
				$scope.doDelete = false;
				$scope.event = null;
			},
			open: function(event) {
				$scope.doDelete = true;
				$scope.event = event;
			}
	};
	
});